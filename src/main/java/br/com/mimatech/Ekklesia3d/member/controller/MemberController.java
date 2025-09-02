package br.com.mimatech.Ekklesia3d.member.controller;

import br.com.mimatech.Ekklesia3d.member.dto.MemberDto;
import br.com.mimatech.Ekklesia3d.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
@Tag(name = "Membros", description = "Endpoints administrativos de membros")
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "Listar todos os membros",
            description = "Retorna a lista completa de membros cadastrados")
    @GetMapping
    public ResponseEntity<List<MemberDto>> findAll() {
        return ResponseEntity.ok(memberService.findAll());
    }

    @Operation(summary = "Buscar membro por ID",
            description = "Retorna os dados de um membro específico")
    @ApiResponse(responseCode = "404", description = "Membro não encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<MemberDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(memberService.findById(id));
    }

    @Operation(summary = "Atualizar membro",
            description = "Atualiza os dados de um membro existente")
    @PutMapping("/{id}")
    public ResponseEntity<MemberDto> update(@PathVariable Long id, @RequestBody MemberDto dto) {
        return ResponseEntity.ok(memberService.update(id, dto));
    }

    @Operation(summary = "Excluir membro",
            description = "Remove um membro do sistema")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        memberService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
