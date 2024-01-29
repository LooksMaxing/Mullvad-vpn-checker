-- Note: Requires penlight Library

local http = require("socket.http")
local json = require("pl.simplejson")

local response,status,content = http.request("https://am.i.mullvad.net/json")
if status ~= 200 then
    print("Error making request to Mullvad API " .. status)
    os.exit(1)
end

local data = json.decode(content)
local mullvadExitIP = data.mullvad_exit_ip

if not mullvadExitIP then
    print("Not connected to mullvad VPN. Aborting...")
    os.exit(1)
end

