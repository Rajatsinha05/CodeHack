package com.project.CodeHack.Repository;

import com.project.CodeHack.Model.UserAccount;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


public interface UserRepository extends MongoRepository<UserAccount,String> {
}
