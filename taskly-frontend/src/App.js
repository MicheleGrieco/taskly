import React, { useState, useEffect } from 'react';
import TaskForm from './components/TaskForm';
import TaskList from './components/TaskList';
import './App.css';

function App() {
  const [tasks, setTasks] = useState([]);

  useEffect(() => {
    fetch('/tasks')
      .then(response => response.json())
      .then(data => setTasks(data))
      .catch(error => console.error('Error fetching tasks:', error));
  }, []);

  const handleAdd = (newTask) => {
    setTasks([...tasks, newTask]);
  };

  const handleToggle = async (id, currentStatus) => {
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

  return (
    <div className="flex flex-col items-center justify-center min-h-screen bg-gray-100 p-4">
      <h1 className="text-4xl font-bold text-gray-800 mb-8">Taskly</h1>
      <div className="bg-white shadow-lg rounded-lg p-6 w-full max-w-md">
        <TaskForm onTaskAdded={handleAdd} />
        <TaskList 
          tasks={tasks} 
          onToggleCompleted={handleToggle} 
          onDelete={handleDelete} 
        />
      </div>
    </div>
  );
}

export default App;