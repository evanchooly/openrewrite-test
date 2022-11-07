/*
 * Copyright (c) 2008-2016 MongoDB, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.morphia.test.models;

import java.util.Arrays;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Property;

import static java.lang.String.format;

@Entity(value = "zipcodes", useDiscriminator = false)
public final class City {
    @Id
    private String id;
    @Property("city")
    private String name;
    @Property("loc")
    private double[] location;
    @Property("pop")
    private Double population;
    @Property("state")
    private String state;

    public String getName() {
        return name;
    }

    public Double getPopulation() {
        return population;
    }

    @Override
    public String toString() {
        return format("City{id='%s', name='%s', location=%s, population=%s, state='%s'}", id, name, Arrays.toString(location),
                population, state);
    }
}
