package com.shms.api.dto.auth.authorities;

import com.shms.api.enums.Authority;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorityDTO {
    private Authority name;
}
