package med.voll.api.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import med.voll.api.domain.user.dto.UserDTO;
import med.voll.api.domain.user.dto.UserTokenDTO;
import med.voll.api.domain.user.entity.User;
import med.voll.api.service.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@AllArgsConstructor
public class AuthenticateController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid UserDTO userDto) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDto.login(), userDto.password());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        String token = tokenService.generateToken((User) authenticate.getPrincipal());
        return ResponseEntity.ok(new UserTokenDTO(token));
    }

}
