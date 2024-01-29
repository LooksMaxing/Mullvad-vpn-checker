$response = Invoke-WebRequest -Uri 'https://am.i.mullvad.net/json'
$mullvadEXitIP = ($response.Content | ConvertFrom-Json).mullvad_exit_ip

if ($mullvadEXitIP -eq $null) {
    Write-Host "Mullvad is not connected. Aborting..."
    exit 1
} else {
    Write-Host "Mullvad is connected"
}

