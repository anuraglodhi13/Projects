import React, {ChangeEvent, useState} from 'react';
import {Button, Grid, TextField, Paper, Card, CardHeader, CardContent, Tooltip} from '@mui/material';
import FileCopyIcon from '@mui/icons-material/FileCopy';


interface LandingPageProps {
    jobProfileInput: string;
    experienceInput: string;
    companyInput: string;
    handleChange: (e: ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => void;
    handleSearch: () => void;
    searchResults: string;
}

const LandingPage: React.FC<LandingPageProps> = ({
                                                     jobProfileInput,
                                                     experienceInput,
                                                     companyInput,
                                                     handleChange,
                                                     handleSearch,
                                                     searchResults,
                                                 }) => {

    const [isCopied, setIsCopied] = useState(false);

    const copyToClipboard = () => {
        navigator.clipboard.writeText(searchResults).then(() => {
            setIsCopied(true);
            setTimeout(() => {
                setIsCopied(false);
            }, 2000);
        });
    };

    return (
        <Grid container spacing={0}>
            <Grid item xs={12} sm={6}>
                <Paper
                    sx={{
                        backgroundImage: 'linear-gradient(135deg, #fff, #f0f8ff)',
                        padding: '2rem',
                        display: 'flex',
                        flexDirection: 'column',
                        justifyContent: 'center', // Center horizontally and vertically
                        alignItems: 'center',
                        minHeight: '100vh',
                    }}
                >
                    <div
                        style={{
                            width: '100%',
                            textAlign: 'center', // Center the button horizontally
                        }}
                    >
                        <TextField
                            type="text"
                            name="jobProfileInput"
                            placeholder="Job Profile"
                            variant="outlined"
                            sx={{ width: '100%', marginBottom: '1rem' }}
                            value={jobProfileInput}
                            onChange={(e) => handleChange(e)}
                        />

                        <TextField
                            type="number"
                            name="experienceInput"
                            placeholder="Experience in years"
                            variant="outlined"
                            sx={{ width: '100%', marginBottom: '1rem' }}
                            value={experienceInput}
                            onChange={(e) => handleChange(e)}
                        />

                        <TextField
                            type="text"
                            name="companyInput"
                            placeholder="Company"
                            variant="outlined"
                            sx={{ width: '100%', marginBottom: '1rem' }}
                            value={companyInput}
                            onChange={(e) => handleChange(e)}
                        />

                        <Button
                            onClick={handleSearch}
                            variant="contained"
                            color="primary"
                            sx={{
                                backgroundColor: (theme) => theme.palette.primary.main,
                                color: 'white',
                                fontWeight: 'bold',
                            }}
                        >
                            Generate Personalized Invite
                        </Button>
                    </div>
                </Paper>
            </Grid>
            <Grid item xs={12} sm={6}>
                <Paper
                    sx={{
                        backgroundImage: 'linear-gradient(135deg, #fff, #f0f8ff)',
                        padding: '2rem',
                        display: 'flex',
                        justifyContent: 'center',
                        alignItems: 'center',
                        minHeight: '100vh',
                    }}
                >
                    <Card
                        sx={{
                            width: '100%',
                            maxWidth: '400px',
                            background: 'rgba(255, 255, 255, 0.2)',
                            backdropFilter: 'blur(10px)',
                            border: '1px solid rgba(255, 255, 255, 0.18)',
                            boxShadow: '0 2px 4px rgba(0, 0, 0, 0.1)',
                        }}
                    >
                        <CardHeader
                            title="Personalized invite for you"
                            sx={{ color: 'black' }} // Text color
                        />
                        <CardContent>
                            <p>{searchResults}</p>
                        </CardContent>
                        <Button
                            onClick={copyToClipboard}
                            variant="contained"
                            color="primary"
                            sx={{
                                margin: '1rem',
                                float: 'right',
                                backgroundColor: (theme) => theme.palette.primary.main,
                                color: 'white',
                            }}
                        >
                            Go To LinkedIn
                        </Button>
                        <Tooltip
                            title={isCopied ? 'Copied to clipboard' : 'Copy to clipboard'}
                            arrow
                        >
                            <Button
                                onClick={copyToClipboard}
                                variant="contained"
                                color="primary"
                                sx={{
                                    margin: '1rem',
                                    float: 'left',
                                    backgroundColor: 'transparent',
                                    color: 'gray',
                                }}
                            >
                                <FileCopyIcon />
                            </Button>
                        </Tooltip>
                    </Card>
                </Paper>
            </Grid>
        </Grid>
    );
};

export default LandingPage;
