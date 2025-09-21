import React, { useState, useEffect } from 'react';
import './App.css';

function App() {
  const [tasks, setTasks] = useState([]);
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');

  // Fetch tasks from the backend API when the component mounts
  useEffect(() => {
    fetch('/tasks')
      .then(response => response.json())
      .then(data => setTasks(data))
      .catch(error => console.error('Error fetching tasks:', error));
  }, []);

  const toggleCompleted = async (id, currentStatus) => {
    const updatedTask = tasks.find(task => task.id === id);
    updatedTask.completed = !currentStatus;

    const response = await fetch(`/tasks/${id}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(updatedTask),
    });

    if (response.ok) {
      const updatedTasks = tasks.map(task => 
        task.id === id ? { ...task, completed: !currentStatus } : task
      );
      setTasks(updatedTasks);
    } else {
      console.error('Failed to update task.');
    }
  };

  const handleDelete = async (id) => {
    const response = await fetch(`/tasks/${id}`, {
      method: 'DELETE',
    });

    if (response.ok) {
      setTasks(tasks.filter(task => task.id !== id));
    } else {
      console.error('Failed to delete task.');
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const newTask = {
      title,
      description,
      completed: false
    };

    const response = await fetch('/tasks', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(newTask),
    });

    if (response.ok) {
      const createdTask = await response.json();
      setTasks([...tasks, createdTask]);
      setTitle('');
      setDescription('');
    } else {
      console.error('Failed to create task.');
    }
  };

  return (
    <div className="flex flex-col items-center justify-center min-h-screen bg-gray-100 p-4">
      <h1 className="text-4xl font-bold text-gray-800 mb-8">Taskly</h1>
      <div className="bg-white shadow-lg rounded-lg p-6 w-full max-w-md">
        <form onSubmit={handleSubmit} className="flex flex-col space-y-2 mb-4">
          <input
            type="text"
            placeholder='Add a new task'
            value={title}
            onChange={(e) => setTitle(e.target.value)}
            className="p-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
          />
          <input
            type="text"
            placeholder='Add a description'
            value={description}
            onChange={(e) => setDescription(e.target.value)}
            className="p-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
          />
          <button
            type="submit"
            className="bg-blue-500 text-white p-2 rounded-md hover:bg-blue-600 transition duration-300"
          >
            Add
          </button>
        </form>
        <ul className="space-y-4">
          {tasks.map(task => (
            <li key={task.id} className="flex justify-between items-center bg-gray-50 p-4 rounded-md shadow-sm">
              <span className={task.completed ? 'line-through text-gray-500' : 'text-gray-800'}>
                <strong className="block text-lg font-semibold">{task.title}</strong>
                <span className="block text-sm text-gray-600">{task.description}</span>
              </span>
              <div className="flex space-x-2">
                <button
                  onClick={() => toggleCompleted(task.id, task.completed)}
                  className="text-sm bg-green-500 text-white p-2 rounded-md hover:bg-green-600 transition duration-300"
                >
                  {task.completed ? 'Undo' : 'Complete'}
                </button>
                <button
                  onClick={() => handleDelete(task.id)}
                  className="text-sm bg-red-500 text-white p-2 rounded-md hover:bg-red-600 transition duration-300"
                >
                  Delete
                </button>
              </div>
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
}

export default App;