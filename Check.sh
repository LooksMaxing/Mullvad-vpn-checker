#!/bin/bash
response=$(curl -s https://am.i.mullvad.net/json)
mullvad_exit_ip=$(echo "$response" | jq -r '.mullvad_exit_ip')

if [ "$mullvad_exit_ip" = "false" ]; then
  echo "Not connected to Mullvad VPN. Aborting..."
  exit 1
fi

# Other checks or additional actions can go here