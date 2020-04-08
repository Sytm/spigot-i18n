/*
 *     A simple utility designed for spigot to make multi-language support easy
 *     Copyright (C) 2020 Lukas Planz
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Lesser General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Lesser General Public License for more details.
 *
 *     You should have received a copy of the GNU Lesser General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package de.md5lukas.i18n.api.language;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * Simple helper for translations as messages
 */
public class Translation {

    private final LanguageStore languageStore;
    private final String key;

    private char altColorChar = '&'; // TODO make customizable

    /**
     * Creates a new translation helper using the language store and the key in the configuration
     *
     * @param languageStore The language store to use to retrieve the language data from
     * @param key           The key of this translation in the configs
     */
    public Translation(LanguageStore languageStore, String key) {
        this.languageStore = languageStore;
        this.key = key;
    }

    /**
     * Gets the string from the current translation in the language of the command sender
     *
     * @param commandSender          The command sender of which the language should be used
     * @param targetsAndReplacements The targets and replacements to use to substitute strings in this translation
     * @return The translated message with substitutions for the targets in place
     */
    public String getAsString(CommandSender commandSender, String... targetsAndReplacements) {
        return ChatColor.translateAlternateColorCodes(altColorChar, StringHelper.multiReplace(languageStore.getLanguage(commandSender).getTranslation(key)));
    }

    /**
     * Gets the message using {@link #getAsString(CommandSender, String...)} and directly sends it to the command sender
     *
     * @param commandSender          The command sender of which the language should be used and the message should be sent to
     * @param targetsAndReplacements The targets and replacements to use to substitute strings in this translation
     */
    public void send(CommandSender commandSender, String targetsAndReplacements) {
        commandSender.sendMessage(ChatColor.translateAlternateColorCodes(altColorChar, getAsString(commandSender, targetsAndReplacements)));
    }
}
