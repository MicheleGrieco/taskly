import React from 'react';

const TaskItem = ({ task, onToggleCompleted, onDelete }) => {
    return (
        <li className="flex justify-between items-center bg-gray-50 p-4 rounded-md shadow-sm">
            <span className={task.completed ? 'line-through text-gray-500' : 'text-gray-800'}>
                <strong className="block text-lg font-semibold">{task.title}</strong>
                <span className="block text-sm text-gray-600">{task.description}</span>
            </span>
            <div className="flex space-x-2">
                <button
                    onClick={() => onToggleCompleted(task.id, task.completed)}
                    className="text-sm bg-green-500 text-white p-2 rounded-md hover:bg-green-600 transition duration-300"
                >
                    {task.completed ? 'Undo' : 'Complete'}
                </button>
                <button
                    onClick={() => onDelete(task.id)}
                    className="text-sm bg-red-500 text-white p-2 rounded-md hover:bg-red-600 transition duration-300"
                >
                    Delete
                </button>
            </div>
        </li>
    );
};

export default TaskItem;