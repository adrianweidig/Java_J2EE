# PowerShell Script zum Kopieren von Code-Dateien
# Kopiert alle Dateien mit spezifischen Erweiterungen in ./AllData
# Ohne sie zu verschieben (nur Kopien)

# Definiere Zielordner
$TargetDir = ".\AllData"

# Erstelle Zielordner, falls nicht vorhanden
if (-not (Test-Path $TargetDir)) {
    New-Item -ItemType Directory -Path $TargetDir | Out-Null
    Write-Host "Ordner '$TargetDir' erstellt." -ForegroundColor Green
}

# Definiere Dateitypen zum Kopieren
$FileExtensions = @("*.jsp", "*.java", "*.xml", "*.html", "*.css", "*.js", "*.gradle", "*.properties", "*.yml", "*.yaml", "*.json")

# Zähler für Statistik
$CopyCount = 0
$ErrorCount = 0

# Kopiere alle Dateien
foreach ($Extension in $FileExtensions) {
    $Files = Get-ChildItem -Path "." -Filter $Extension -Recurse -File
    
    foreach ($File in $Files) {
        try {
            Copy-Item -Path $File.FullName -Destination $TargetDir -Force
            Write-Host "✓ Kopiert: $($File.FullName)" -ForegroundColor Green
            $CopyCount++
        }
        catch {
            Write-Host "✗ Fehler beim Kopieren von $($File.FullName): $_" -ForegroundColor Red
            $ErrorCount++
        }
    }
}

# Ausgabe Zusammenfassung
Write-Host "`n========================================" -ForegroundColor Cyan
Write-Host "Kopiervorgang abgeschlossen!" -ForegroundColor Cyan
Write-Host "Dateien kopiert: $CopyCount" -ForegroundColor Green
Write-Host "Fehler: $ErrorCount" -ForegroundColor $(if ($ErrorCount -gt 0) { "Red" } else { "Green" })
Write-Host "Zielordner: $TargetDir" -ForegroundColor Yellow
Write-Host "========================================`n" -ForegroundColor Cyan
