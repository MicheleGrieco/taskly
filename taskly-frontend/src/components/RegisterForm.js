import React, { useState } from 'react';

function RegisterForm() {
    const [form, setForm] = useState({ username: "", password: "" });
    const [message, setMessage] = useState("");

    const handleChange = e => {
        setForm({ ...form, [e.target.name]: e.target.value });
    };

    const handleSubmit = async e => {
        e.preventDefault();
        try {
            const res = await fetch("https://localhost:8080/users/register", {
                method: "POST",
                headers: { "Content-Type": "application/json"},
                body: JSON.stringify(form)
            });
            if (res.ok) {
                setMessage("Registration successful!");
            } else {
                const data = await res.json();
                setMessage(data.message || "Error during registration.");
            }
        } catch (err) {
            setMessage("Network error.");
        }
    };

    return (
        <form onSubmit={handleSubmit} className="p-4">
            <input
                name="username"
                placeholder="Username"
                value={form.username}
                onChange={handleChange}
                className="border p-2 m-2"
                required
            />
            <input
                name="password"
                type="password"
                placeholder="Password"
                value={form.password}
                onChange={handleChange}
                className="border p-2 m-2"
                required
            />
            <button type="submit" className="bg-blue-500 text-white p-2 m-2">Register</button>
            {message && <div className="mt-2 text-red-500">{message}</div>}
        </form>
    );
}

export default RegisterForm;