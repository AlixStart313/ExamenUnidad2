package mx.edu.utez.user.service.user;


import mx.edu.utez.user.model.user.IUserRepository;
import mx.edu.utez.user.model.user.UserModel;
import mx.edu.utez.user.utils.CustomReponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class UserService {
    @Autowired
    private IUserRepository userRepository;

    @Transactional(readOnly = true)
    public CustomReponse<List<UserModel>> getAll(){
        return new CustomReponse<>(
                this.userRepository.findAll(),
                false,
                200,
                "ok"
        );
    }

    @Transactional(readOnly = true)
    public CustomReponse<UserModel> getOne(Long id){
        return new CustomReponse<>(
                this.userRepository.findById(id).get(),
                false,
                200,
                "ok"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomReponse<UserModel> insert(UserModel user) {
        Optional<UserModel> existsByUsername = this.userRepository.findByUsername(user.getUsername());
        Optional<UserModel> existsByCurp = this.userRepository.findByCurp(user.getUsername());
        if (existsByUsername.isPresent() && existsByCurp.isPresent()) {
            return new CustomReponse<>(
                    null,
                    true,
                    400,
                    "Este Usuario ya se encuentra registrado "
            );
        }
        return new CustomReponse<>(
                this.userRepository.saveAndFlush(user),
                false,
                200,
                "Usuario registrado correctamente"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomReponse<Integer> changeStatus(UserModel userModel){
        if (!this.userRepository.existsById(userModel.getId())){
            return new CustomReponse<>(
                    0,
                    true,
                    400,
                    "No se pudo modiifcar el status de este usuario"

            );

        }

        return  new CustomReponse<>(
                this.userRepository.updateStatusById(userModel.isStatus(), userModel.getId()),false,200,"ok"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomReponse<UserModel> update(UserModel userModel) {
        Optional<UserModel> existsById = this.userRepository.findById(userModel.getId());
        if (!existsById.isPresent()) {
            return new CustomReponse<>(
                    null,
                    true,
                    400,
                    "Este Usuario no se encuentra registrado "
            );
        }
        return new CustomReponse<>(
                this.userRepository.saveAndFlush(userModel),
                false,
                200,
                "persona modifica correctamente"
        );
    }


}
