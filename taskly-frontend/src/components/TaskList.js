import React from 'react';
import TaskItem from './TaskItem';

const TaskList = ({ tasks, onToggleCompleted, onDelete}) => {
    return (
        <ul className="space-y-4">
            {tasks.map(task => (
                <TaskItem
                    key={task.id}
                    task={task}
                    onToggleCompleted={onToggleCompleted}
                    onDelete={onDelete}
                />
            ))}
        </ul>
    );
};

export default TaskList;