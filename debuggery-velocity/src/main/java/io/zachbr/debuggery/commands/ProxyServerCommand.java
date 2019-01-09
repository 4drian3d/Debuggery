/*
 * This file is part of Debuggery.
 *
 * Debuggery is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Debuggery is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Debuggery.  If not, see <http://www.gnu.org/licenses/>.
 */

package io.zachbr.debuggery.commands;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.ProxyServer;
import io.zachbr.debuggery.DebuggeryVelocity;
import io.zachbr.debuggery.commands.base.CommandReflection;
import org.jetbrains.annotations.NotNull;

public class ProxyServerCommand extends CommandReflection {
    private final DebuggeryVelocity debuggery;

    public ProxyServerCommand(DebuggeryVelocity plugin) {
        super("vproxy", "debuggery.vproxy", false, ProxyServer.class, plugin);
        this.debuggery = plugin;
    }

    @Override
    protected void commandLogic(@NotNull CommandSource source, @NotNull String[] args) {
        doReflectionLookups(source, args, debuggery.getProxyServer());
    }
}
