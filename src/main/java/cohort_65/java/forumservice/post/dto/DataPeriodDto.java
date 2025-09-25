package cohort_65.java.forumservice.post.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDate;
@Getter
public class DataPeriodDto {

    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate dateFrom;
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate dateTo;
}
