package com.lambdaschool.shoppingcart.services;

import com.lambdaschool.shoppingcart.exceptions.ResourceNotFoundException;
import com.lambdaschool.shoppingcart.models.Roles;
import com.lambdaschool.shoppingcart.repositories.RoleRepository;
import com.lambdaschool.shoppingcart.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "roleService")
public class RolesServiceImpl implements RolesService
{
    @Autowired
    RoleRepository rolerepos;

    @Autowired
    UserRepository userrepos;

    @Autowired
    private UserAuditing userAuditing;

    public List<Roles> findAll()
    {
        List<Roles> list = new ArrayList<>();
        rolerepos.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }

    @Override
    public Roles findRoleById(long id)
    {
        return rolerepos.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role id " + id + " not found!"));
    }

    @Transactional
    @Override
    public Roles save(Roles role)
    {
        if (role.getUsers().size() > 0)
        {
            throw new ResourceNotFoundException("User ROles are not updated through " +
                    "Role.");
        }
        return rolerepos.save(role);
    }
}
