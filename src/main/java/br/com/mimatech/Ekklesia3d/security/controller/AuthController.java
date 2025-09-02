package br.com.mimatech.Ekklesia3d.security.controller;

import br.com.mimatech.Ekklesia3d.member.dto.MemberDto;
import br.com.mimatech.Ekklesia3d.member.dto.MemberRegisterDto;
import br.com.mimatech.Ekklesia3d.member.entities.Member;
import br.com.mimatech.Ekklesia3d.member.service.MemberService;
import br.com.mimatech.Ekklesia3d.security.dto.LoginRequestDto;
import br.com.mimatech.Ekklesia3d.security.dto.TokenResponseDto;
import br.com.mimatech.Ekklesia3d.security.service.JwtService;
import br.com.mimatech.Ekklesia3d.shared.enums.AuthProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Autenticação", description = "Endpoints para registro e login de usuários")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final MemberService memberService;

    @Operation(
            summary = "Login local",
            description = "Autentica um usuário local com email e senha e retorna um JWT.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Login bem sucedido",
                            content = @Content(schema = @Schema(implementation = TokenResponseDto.class))),
                    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
            }
    )
    @RequestBody(
            description = "Credenciais de login",
            required = true,
            content = @Content(
                    schema = @Schema(implementation = LoginRequestDto.class),
                    examples = {
                            @ExampleObject(
                                    name = "Exemplo Login Local",
                                    value = """
                {
                  "email": "misael@email.com",
                  "password": "strongPassword123"
                }
                """
                            )
                    }
            )
    )
    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody LoginRequestDto request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );

        if (auth.isAuthenticated()) {
            String token = jwtService.generateToken((Member) auth.getPrincipal());
            return ResponseEntity.ok(new TokenResponseDto(token));
        } else {
            throw new RuntimeException("Credenciais inválidas");
        }
    }


    @Operation(
            summary = "Registrar usuário local",
            description = "Cria um novo usuário local no sistema com email e senha.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuário criado com sucesso",
                            content = @Content(schema = @Schema(implementation = MemberDto.class))),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos")
            }
    )
    @RequestBody(
            description = "Dados para registro de membro local",
            required = true,
            content = @Content(
                    schema = @Schema(implementation = MemberRegisterDto.class),
                    examples = {
                            @ExampleObject(
                                    name = "Exemplo Registro Local",
                                    value = """
                {
                  "name": "Misael Souza Marcelino",
                  "email": "misael@email.com",
                  "phone": "+55 11 99999-9999",
                  "age": 28,
                  "dateOfBirth": "1997-05-15",
                  "dateOfBaptism": "2015-09-01",
                  "address": {
                    "street": "Rua das Flores",
                    "number": "123",
                    "complement": "Apto 45",
                    "city": "São Paulo",
                    "state": "SP",
                    "zipCode": "01000-000"
                  },
                  "position": {
                    "position": "Diácono",
                    "description": "Responsável por auxiliar no serviço da igreja"
                  },
                  "password": "strongPassword123",
                  "role": "USER"
                }
                """
                            )
                    }
            )
    )
    @PostMapping("/register")
    public ResponseEntity<MemberDto> register(@RequestBody MemberRegisterDto dto){
        MemberDto created = memberService.registerLocal(dto);
        return ResponseEntity.ok(created);
    }


    @Operation(
            summary = "Login ou registro via OAuth",
            description = "Permite login/registro via Google, Facebook ou Apple.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Login/registro social bem sucedido",
                            content = @Content(schema = @Schema(implementation = MemberDto.class))),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos")
            }
    )

    @PostMapping("/oauth")
    public ResponseEntity<MemberDto> loginOAuth(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String providerId,
            @RequestParam AuthProvider provider) {

        MemberDto member = memberService.registerOAuth(name, email, providerId, provider);
        return ResponseEntity.ok(member);
    }

}
