package meli.com.apifut.Repository;

import meli.com.apifut.DTO.EstadioDTO;
import meli.com.apifut.Model.Estadio;
import meli.com.apifut.Service.EstadioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DataJpaTest
@ActiveProfiles("test")
class EstadioRepositoryTest {

    @Mock
    EstadioRepository estadioRepository;

    @Mock
    EstadioService estadioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve retornar um estadio quando o nome existir no banco de dados")
    void findEstadioByNomeSuccess() {
        // Arrange
        String nome = "Mineirão";
        Estadio estadio = new Estadio(nome);
        when(estadioRepository.findEstadioByNome(nome)).thenReturn(estadio);

        // Act
        Estadio result = estadioRepository.findEstadioByNome(nome);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getNome()).isEqualTo(nome);
    }

    @Test
    @DisplayName("Não deve retornar nada se o nome do estádio não existir no banco de dados")
    void findEstadioByNomeFailed() {
        // Arrange
        String nome = "Estádio Inexistente";
        when(estadioRepository.findEstadioByNome(nome)).thenReturn(null);

        // Act
        Estadio result = estadioRepository.findEstadioByNome(nome);

        // Assert
        assertThat(result).isNull();
    }

    @Test
    @DisplayName("Deve retornar um estadio quando o id existir no banco de dados")
    void findEstadioByIdSuccess() {
        String nome = "Mineirão";
        Long id = 1L;
        when(estadioRepository.findEstadioById(id)).thenReturn(new Estadio("Mineirão", 1L));

        Estadio result = estadioRepository.findEstadioById(id);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(id);
        assertThat(result.getNome()).isEqualTo("Mineirão");
    }

    @Test
    @DisplayName("Não deve retornar nada se o id do estádio não existir no banco de dados")
    void findEstadioByIdFailed() {
        Long id = 10L;
        when(estadioRepository.findEstadioById(id)).thenReturn(null);

        Estadio result = estadioRepository.findEstadioById(id);

        assertThat(result).isNull();
    }

    @Test
    @DisplayName("Deve criar um estadio com sucesso")
    void criarEstadioSuccess() {
        // Arrange
        String nome = "Mineirão";
        EstadioDTO estadioDTO = new EstadioDTO();
        estadioDTO.setNome(nome);
        Estadio estadio = new Estadio(estadioDTO);
        when(estadioRepository.save(estadio)).thenReturn(estadio);

        // Act
        Estadio result = estadioRepository.save(estadio);

        // Assert
        assertThat(result).isNotNull();

    }

}
