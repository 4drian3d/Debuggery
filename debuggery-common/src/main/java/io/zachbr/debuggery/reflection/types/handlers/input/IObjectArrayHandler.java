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

package io.zachbr.debuggery.reflection.types.handlers.input;

import io.zachbr.debuggery.reflection.types.TypeHandler;
import io.zachbr.debuggery.reflection.types.handlers.base.IPolymorphicHandler;
import io.zachbr.debuggery.reflection.types.handlers.base.platform.PlatformSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Array;
import java.util.Arrays;

public class IObjectArrayHandler implements IPolymorphicHandler {
    private final TypeHandler typeHander;

    public IObjectArrayHandler(TypeHandler handler) {
        this.typeHander = handler;
    }

    @Override
    public @NotNull Object[] instantiateInstance(String input, Class<?> clazz, @Nullable PlatformSender<?> sender) throws Exception {
        String[] elementsIn = input.split(",");
        Class<?> arrayType = clazz.getComponentType();

        Class[] neededTypes = new Class[elementsIn.length];
        Arrays.fill(neededTypes, arrayType);

        Object[] untyped = typeHander.instantiateTypes(neededTypes, Arrays.asList(elementsIn));
        Object[] typedArray = (Object[]) Array.newInstance(arrayType, elementsIn.length);
        System.arraycopy(untyped, 0, typedArray, 0, untyped.length);

        return typedArray;
    }

    @Override
    public @NotNull Class<?> getRelevantClass() {
        return Object[].class;
    }
}
