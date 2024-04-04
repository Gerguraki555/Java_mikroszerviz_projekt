package org.example.Models;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Asztal azonosító")
public class TableIdRequest {
    @Schema(description = "Az asztal belső azonosítója")
    private long id;
}