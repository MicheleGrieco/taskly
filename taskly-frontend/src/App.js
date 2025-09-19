import React, { useState, useEffect } from 'react';
import './App.css';

function App() {
  const [tasks, setTask] = useState([]);
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');

  useEffect(() => {
    fetch('/tasks')
      .then(response => response.json())
      .then(data => setTask(data))
      .catch(error => console.error('Error fetching tasks:', error));
  }, []); // Empty dependency array to run only once on mount

  const toggleCompleted = async (id, currentStatus) => {
    const updatedTask = tasks.find(task => task.id === id);
    updatedTask.completed = !currentStatus;

    const response = await fetch(`/tasks/${id}`, { // Backtick for template literal `
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
      setTask(updatedTasks);
    } else {
      console.error('Failed to update task.');
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
      setTask([...tasks, createdTask]);
      setTitle('');
      setDescription('');
    } else {
      console.error('Failed to create task.');
    }
  };

  return (
    <div className="App">
      <h1>Taskly</h1>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder='Add a new task'
          value={title}
          onChange={(e) => setTitle(e.target.value)}
        />
        <input
          type="text"
          placeholder='Add a description'
          value={description}
          onChange={(e) => setDescription(e.target.value)}
        />
        <button type="submit">Add</button>
      </form>
      <ul>
        {tasks.map(task => (
          <li key={task.id}>
            <span style={{ textDecoration: task.completed ? 'line-through' : 'none' }}>
              <strong>{task.title}</strong>: {task.description}
            </span>
            <button onClick={() => toggleCompleted(task.id, task.completed)}>
              {task.completed ? 'Delete' : 'Complete'}
            </button>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default App;