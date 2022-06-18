package chao.wonderland.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorHeaderDTO {

    private String code;

    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String target;

}