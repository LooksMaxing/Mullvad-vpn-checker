import requests
import json
import sys

response = requests.get('https://am.i.mullvad.net/json')
response.raise_for_status()
data = response.json()

mullvad_exit_ip = data.get('mullvad_exit_ip', False)

if not mullvad_exit_ip:
    print("Not connected to Mullvad VPN. Aborting...")
    sys.exit(1)

# Other checks or additional actions can go here