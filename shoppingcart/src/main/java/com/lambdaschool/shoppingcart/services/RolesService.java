package com.lambdaschool.shoppingcart.services;

import com.lambdaschool.shoppingcart.models.Roles;

import java.util.List;

public interface RolesService
{
    List<Roles> findAll();

    Roles findRoleById(long id);

    Roles save(Roles role);

}
