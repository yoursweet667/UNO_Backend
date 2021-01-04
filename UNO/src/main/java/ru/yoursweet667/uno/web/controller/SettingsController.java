package ru.yoursweet667.uno.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yoursweet667.uno.web.model.Settings;

@RestController
public class SettingsController {
    /**
     * Get client settings
     */
    @GetMapping("settings")
    public Settings getClientSettings() {
        return new Settings(50);
    }
}