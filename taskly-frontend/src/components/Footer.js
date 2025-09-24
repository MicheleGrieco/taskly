import React from "react";

const Footer = () => {
    return (
        <footer className="w-full text-center p-4 bg-gray-100 text-gray-600 absolute-bottom-0">
            <p>
                Follow me on 
                <a
                 href="https://www.github.com/MicheleGrieco"
                 target="_blank"
                 rel="noopener noreferrer"
                 className="text-blue-500 hover:underline ml-1">
                    GitHub
                </a>
                .
            </p>
        </footer>
    );
};

export default Footer;