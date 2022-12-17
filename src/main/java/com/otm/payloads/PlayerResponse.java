package com.otm.payloads;

import com.otm.model.dto.PlayerDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class PlayerResponse {

    private List<PlayerDto> content;

    private int pageNumber;

    private int PageSize;

    private long totalElements;

    private int totalPages;

    private boolean lastPage;

}
