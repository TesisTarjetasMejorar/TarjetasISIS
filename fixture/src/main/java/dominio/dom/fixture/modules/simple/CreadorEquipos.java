/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package dominio.dom.fixture.modules.simple;


import org.apache.isis.applib.fixturescripts.FixtureScript;

import dominio.dom.equipo.Equipo;
import dominio.dom.equipo.Equipos;

public class CreadorEquipos extends FixtureScript {

    //region > nombre (input)
    private String nombre;
    /**
     * Name of the object (required)
     */
    public String getNombre() {
        return nombre;
    }

    public CreadorEquipos setNombre(final String name) {
        this.nombre = name;
        return this;
    }
    //endregion


    //region > equipo (output)
    private Equipo equipo;

    /**
     * The created simple object (output).
     * @return
     */
    public Equipo getEquipo() {
        return equipo;
    }
    //endregion

    @Override
    protected void execute(final ExecutionContext ec) {

        String name = checkParam("nombre", ec, String.class);

        this.equipo = wrap(equipos).Cargar(name);	


        // also make available to UI
        ec.addResult(this, equipo);
    }

    @javax.inject.Inject
    private Equipos equipos;

}
