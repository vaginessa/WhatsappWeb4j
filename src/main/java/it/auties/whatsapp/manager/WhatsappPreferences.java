package it.auties.whatsapp.manager;

import com.fasterxml.jackson.core.type.TypeReference;
import it.auties.whatsapp.util.JacksonProvider;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
public final class WhatsappPreferences implements JacksonProvider {
    private static final Path DEFAULT_DIRECTORY
            = Path.of(System.getProperty("user.home") + "/.whatsappweb4j/");

    @NonNull
    private final Path file;

    private String cache;

    @SneakyThrows
    public static WhatsappPreferences of(String path, Object... args) {
        var location = Path.of("%s/%s".formatted(DEFAULT_DIRECTORY, path.formatted(args)));
        return new WhatsappPreferences(location.toAbsolutePath());
    }

    @SneakyThrows
    public Optional<String> read() {
        if(Files.notExists(file)){
            return Optional.empty();
        }

        return Optional.of(Objects.requireNonNullElseGet(cache,
                this::readInternal));
    }

    @SneakyThrows
    public <T> T readJson(TypeReference<T> reference) {
        var json = read();
        return json.isEmpty() ? null :
                readAsInternal(json.get(), reference);
    }

    @SneakyThrows
    private <T> T readAsInternal(String value, TypeReference<T> reference) {
        return JACKSON.readValue(value, reference);
    }

    @SneakyThrows
    private String readInternal() {
        return this.cache = Files.readString(file);
    }

    @SneakyThrows
    public void writeJsonAsync(@NonNull Object input){
        CompletableFuture.runAsync(() -> writeInternal(input));
    }

    @SneakyThrows
    private void writeInternal(@NonNull Object input) {
        Files.createDirectories(file.getParent());
        Files.writeString(file, JACKSON.writeValueAsString(input),
                StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    @SneakyThrows
    public void delete() {
        Files.deleteIfExists(file);
    }
}