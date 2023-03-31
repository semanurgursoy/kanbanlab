package com.kanban.kanbanlab.business.concretes;

import com.kanban.kanbanlab.business.abstracts.UserService;
import com.kanban.kanbanlab.dataAccess.abstracts.RoleRepository;
import com.kanban.kanbanlab.dataAccess.abstracts.UserRepository;
import com.kanban.kanbanlab.entities.concretes.Card;
import com.kanban.kanbanlab.entities.concretes.Role;
import com.kanban.kanbanlab.entities.concretes.User;
import com.kanban.kanbanlab.entities.dto.SignUpDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserManager implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private  RoleRepository roleRepository;
    @Autowired
    private  PasswordEncoder encoder;

    @Autowired
    public UserManager(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public List<List<Card>> getCardsByState(int userId){
        User user = userRepository.getById(userId);
        List<Card> cards = user.getCards();

        List<Card> listForToDo = new ArrayList<Card>();
        List<Card> listForDoing = new ArrayList<Card>();
        List<Card> listForDone = new ArrayList<Card>();
        List<List<Card>> listForTotal = new ArrayList<List<Card>>();

        for (Card card: cards) {
            if(card.getState() == 0) listForToDo.add(card);
            else if(card.getState() == 1) listForDoing.add(card);
            else if(card.getState() == 2) listForDone.add(card);
        }

        listForTotal.add(listForToDo);
        listForTotal.add(listForDoing);
        listForTotal.add(listForDone);
        return listForTotal;
    }

    @Override
    public List<Card> getAllCardsByUser(int userId){
        return userRepository.findById(userId).get().getCards();
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findUserByUserName(String name) {return userRepository.findByName(name); }

    @Override
    public User getById(int id) { return userRepository.getById(id); }

    @Override
    public void add(SignUpDto signUpDto) {

        Role role = roleRepository.findByRole("user");
        if (role == null)
            role = roleRepository.save(new Role("user"));

        User user = new User();
        user.setName(signUpDto.getName());
        user.setSurname(signUpDto.getSurname());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(encoder.encode(signUpDto.getPassword()));
        user.setRole(role);

        userRepository.save(user);
    }

    @Override
    public void forgotPassword(String email) {
        //
    }

    @Override
    public void invite(String email) {
        //
    }

    @Override
    public boolean existById(int id){
        return userRepository.existsById(id);
    }

    @Override
    public boolean existByEmail(String email){ return userRepository.existsByEmail(email); }
}
