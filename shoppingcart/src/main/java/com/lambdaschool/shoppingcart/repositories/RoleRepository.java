package com.lambdaschool.shoppingcart.repositories;

import com.lambdaschool.shoppingcart.models.Roles;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Roles, Long>
{
}
