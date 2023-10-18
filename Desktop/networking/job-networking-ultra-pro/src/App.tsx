import React, { useState } from 'react';
import LandingPage from './LandingPage';
import Navbar from "./Navbar";
import Footer from "./Footer";
import axios from "axios";

const App: React.FC = () => {
  const [state, setState] = useState({
    jobProfileInput: '',
    experienceInput: '',
    companyInput: '',
    searchResults: '',
  });

  const handleSearch = () => {
    // You will need to implement the logic to fetch job search results from LinkedIn's API here.
    // Update state.searchResults with the fetched data.
          console.log("state.jobProfileInput: ", state.jobProfileInput);
          console.log("state.experienceInput: ", state.experienceInput);
          console.log("state.companyInput: ", state.companyInput);
          // Define your API URL
          const apiUrl = 'https://us-central1-bulbalist.cloudfunctions.net/generatePersonalizedInvite';

          // Make an Axios GET request
          axios
              .post(apiUrl, {
                  "jobProfile": state.jobProfileInput,
                  "experience": state.experienceInput,
                  "company": state.companyInput
              })
              .then((response: { data: any; }) => {
                  // Log the API response
                  console.log('API Response:', response.data);

                  // Update state.searchResults with the fetched data
                  setState((prevState) => ({
                      ...prevState,
                      searchResults: response.data,
                  }));
              })
              .catch((error) => {
                  // Handle any errors here
                  console.error('API Error:', error);
              });
  };

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    const { name, value } = e.target;
    setState((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };

  return (
        <div className="app">
          <Navbar />
          <LandingPage
              jobProfileInput={state.jobProfileInput}
              experienceInput={state.experienceInput}
              companyInput={state.companyInput}
              handleChange={handleChange}
              handleSearch={handleSearch}
              searchResults={state.searchResults}
          />
          <Footer/>
        </div>
  );
};

export default App;
