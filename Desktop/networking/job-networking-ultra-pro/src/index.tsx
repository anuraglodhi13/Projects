import React from 'react';
import { createRoot } from 'react-dom/client';
import {createTheme, ThemeProvider} from '@mui/material/styles'; // Import ThemeProvider
import App from './App';
import './scss/main.scss'; // Import your main SCSS entry point
import reportWebVitals from './reportWebVitals';

// Create a Material-UI theme
const theme = createTheme();
const container = document.getElementById('root');
const root = createRoot(container!); // createRoot(container!) if you use TypeScript
root.render(
    <ThemeProvider theme={theme}>
        <React.StrictMode>
            <App />
        </React.StrictMode>
    </ThemeProvider>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
