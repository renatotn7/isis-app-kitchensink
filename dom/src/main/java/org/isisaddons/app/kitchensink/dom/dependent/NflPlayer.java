/*
 *  Copyright 2014 Dan Haywood
 *
 *  Licensed under the Apache License, Version 2.0 (the
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
package org.isisaddons.app.kitchensink.dom.dependent;

import java.util.List;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;
import org.isisaddons.app.kitchensink.dom.Entity;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.util.ObjectContracts;

@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(
        strategy=javax.jdo.annotations.IdGeneratorStrategy.IDENTITY,
         column="id")
@javax.jdo.annotations.Version(
        strategy=VersionStrategy.VERSION_NUMBER, 
        column="version")
@ObjectType("NFLPLAYER")
@Bookmarkable
public class NflPlayer implements Entity<NflPlayer> {

    //region > name (property)

    private String name;

    @Column(allowsNull="false")
    @Title(sequence="1")
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    //endregion


    //region > league (property)
    private NflLeague league;

    @Disabled
    @Column(allowsNull = "false")
    @MemberOrder(sequence = "1")
    public NflLeague getLeague() {
        return league;
    }

    public void setLeague(final NflLeague league) {
        this.league = league;
    }
    //endregion

    //region > region (property)
    private NflRegion region;

    @Disabled
    @Column(allowsNull = "true")
    @MemberOrder(sequence = "1")
    public NflRegion getRegion() {
        return region;
    }

    public void setRegion(final NflRegion region) {
        this.region = region;
    }
    //endregion

    //region > team (property)
    private NflTeam team;

    @Disabled
    @Column(allowsNull = "true")
    @MemberOrder(sequence = "1")
    public NflTeam getTeam() {
        return team;
    }

    public void setTeam(final NflTeam team) {
        this.team = team;
    }
    //endregion


    //region > update (action)

    public NflPlayer update(
            final NflLeague league,
            final @Optional NflRegion region,
            final @Optional NflTeam nflTeam) {

        setLeague(league);
        setRegion(region);
        setTeam(nflTeam);

        return this;
    }

    public List<NflRegion> choices1Update(NflLeague league) {
        return NflRegion.thoseFor(league);
    }
    public List<NflTeam> choices2Update(NflLeague league, NflRegion region) {
        return NflTeam.thoseFor(region);
    }

    public NflLeague default0Update() {
        return getLeague();
    }
    public NflRegion default1Update() {
        return getRegion();
    }
    public NflTeam default2Update() {
        return getTeam();
    }
    //endregion


    //region > compareTo

    @Override
    public int compareTo(NflPlayer other) {
        return ObjectContracts.compare(this, other, "name");
    }

    //endregion

    //region > injected services

    @javax.inject.Inject
    @SuppressWarnings("unused")
    private DomainObjectContainer container;

    @javax.inject.Inject
    private NflPlayers nflPlayers;

    //endregion

}
