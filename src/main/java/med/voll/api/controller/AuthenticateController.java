package med.voll.api.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import med.voll.api.domain.user.dto.UserDTO;
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

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid UserDTO userDto) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(userDto.login(), userDto.password());
        Authentication authenticate = authenticationManager.authenticate(token);

        return ResponseEntity.ok().build();
    }

}
