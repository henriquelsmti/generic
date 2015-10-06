/* 
 * Copyright 2015 Henrique Luiz da Silva Mota
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/
package br.com.generic.dao;

import java.util.List;


public abstract interface BaseDAO<T> {

    public T insert(T entity);
    
    public T delete(T entity);

    public T update(T entity);

    public T disassociate(T entity);

    public T findEntityForId(long id);

    public List<T> list(int beginning, int end, String order);

    public List<T> findEntitiesForProperties(int beginning, int end, String order, String names, Object... values);

    public T findEntityForProperties(String names, Object... values);

    public <E> List<E> findFieldsForProperties(int beginning, int end, String order, String field, String names, Object... values);

    public<E> E findFieldForProperties(String field, String names, Object... values);
}
