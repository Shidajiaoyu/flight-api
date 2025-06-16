package com.flight.flight_api.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

// 订单查询请求Dto
@Data
public class FetchBookingsRequest {

    // 订单查询状态
    @Pattern(regexp = "Upcoming|Past", message = "Status must be Upcoming or Past")
    private String status = "Upcoming";
}
