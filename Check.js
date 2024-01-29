const axios = require('axios');
const fs = require('fs');

axios.get('https://am.i.mullvad.net/json')
  .then(response => {
    const data = response.data;
    const mullvadExitIp = data.mullvad_exit_ip;

    if (!mullvadExitIp) {
      console.log("Not connected to Mullvad VPN. Aborting...");
      process.exit(1);
    }

    // Other checks or additional actions can go here
  })
  .catch(error => {
    if (error.response) {
      console.error(`Mullvad API responded with status ${error.response.status}: ${error.response.statusText}`);
    } else if (error.request) {
      console.error('No response received from Mullvad API');
    } else {
      console.error('Error making request to Mullvad API:', error.message);
    }

    process.exit(1);
  });
