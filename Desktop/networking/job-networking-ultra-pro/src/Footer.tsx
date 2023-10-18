import React from 'react';
import { Paper, Typography, Link, Container } from '@mui/material';

const Footer: React.FC = () => {
    const footerStyles: React.CSSProperties = {
        background: 'rgba(255, 255, 255, 0.2)',
        backdropFilter: 'blur(10px)',
        border: '1px solid rgba(255, 255, 255, 0.18)',
        boxShadow: '0 2px 4px rgba(0, 0, 0, 0.1)',
        position: 'fixed',
        bottom: 0,
        width: '100%',
    };

    const contentStyles: React.CSSProperties = {
        padding: '16px',
        display: 'flex',
        justifyContent: 'space-between',
        alignItems: 'center',
    };

    const linkStyles: React.CSSProperties = {
        color: 'black', // Change text color to black
        textDecoration: 'none',
    };

    return (
        <Paper elevation={0} style={footerStyles}>
            <Container>
                <div style={contentStyles}>
                    <Typography variant="body2" style={{ color: 'black' }}>
                        &copy; {new Date().getFullYear()} Your Company. All rights reserved.
                    </Typography>
                    <div style={{ display: 'flex', gap: '16px' }}>
                        <Link href="/terms" style={linkStyles}>
                            Terms
                        </Link>
                        <Link href="/policies" style={linkStyles}>
                            Policies
                        </Link>
                        <Link href="/copyright" style={linkStyles}>
                            Copyright
                        </Link>
                        <Link href="/faq" style={linkStyles}>
                            FAQ
                        </Link>
                        <Link href="/contact" style={linkStyles}>
                            Contact Us
                        </Link>
                    </div>
                </div>
            </Container>
        </Paper>
    );
};

export default Footer;
