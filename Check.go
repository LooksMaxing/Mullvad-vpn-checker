package main

import (
	"encoding/json"
	"fmt"
	"io/ioutil"
	"net/http"
)

type MullvadResponse struct {
	MullvadExitIP string `json:"mullvad_exit_ip"`
}

func main() {
	resp, err := http.Get("https://am.i.mullvad.netjson")
	if err != nil {
		fmt.Println("Error making request to Mullvad API:", err)
		return
	}
	defer resp.Body.Close()

	body, err := ioutil.ReadAll(resp.Body)
	if err != nil {
		fmt.Println("Error reading response body:", err)
		return
	}

	var mullvadResponse MullvadResponse
	err = json.Unmarshal(body, &mullvadResponse)
	if err != nil {
		fmt.Println("Error unmarshalling response:", err)
		return
	}

	if mullvadResponse.MullvadExitIP == "" {
		fmt.Println("Not connected to Mullvad VPN. Aborting...")
		return
	}

	// Other checks or additional actions can go here
}