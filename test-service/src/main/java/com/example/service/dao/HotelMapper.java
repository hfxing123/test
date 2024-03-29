/**
 *    Copyright 2015-2016 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.example.service.dao;

import com.example.common.entry.hotel.Hotel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Eduardo Macarron
 */
@Repository
@Mapper
public interface HotelMapper {

	Hotel selectByCityId(int city_id);

    Hotel selectByCityId4Update(int city_id);

    int updateCount(Hotel record);

    List<Hotel> findList(Hotel record);

    int insert(Hotel record);

}
