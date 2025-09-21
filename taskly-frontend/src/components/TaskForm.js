import React, { useState } from 'react';

const TaskForm = ({ onTaskAdded }) => {
    const [title, setTitle] = useState('');
    const [description, setDescription] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        const newTask = {
            title,
            description,
            completed:false
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
            onTaskAdded(createdTask); // This function updates the state in the parent component
            setTitle('');
            setDescription('');
        } else {
            console.error('Failed to create task.');
        }
    };

    return (
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
    );
};

export default TaskForm;