import React from 'react';
import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import { styled } from '@mui/material/styles';

const GlassmorphicNavbar = styled(AppBar)`
  position: fixed; /* Fixed position on top */
  top: 0;
  background: rgba(255, 255, 255, 0.2);
  backdrop-filter: blur(10px);
  border: 1px solid rgba(255, 255, 255, 0.18);
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
`;

const Navbar: React.FC = () => {
    return (
        <GlassmorphicNavbar>
            <Toolbar>
                <Typography variant="h6" component="div" sx={{ flexGrow: 1, color: 'text.primary' }}>
                    Job Networking Pro
                </Typography>
                {/*<IconButton style={iconStyle}>*/}
                {/*    <SearchIcon />*/}
                {/*</IconButton>*/}
                {/*<IconButton style={iconStyle}>*/}
                {/*    <NotificationsIcon />*/}
                {/*</IconButton>*/}
                {/*<IconButton style={iconStyle}>*/}
                {/*    <MailOutlineIcon />*/}
                {/*</IconButton>*/}
                {/*<IconButton style={iconStyle}>*/}
                {/*    <AccountCircleIcon />*/}
                {/*</IconButton>*/}
            </Toolbar>
        </GlassmorphicNavbar>
    );
};

export default Navbar;
