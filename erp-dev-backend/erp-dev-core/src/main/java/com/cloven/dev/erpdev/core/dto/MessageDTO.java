package com.cloven.dev.erpdev.core.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO  implements Serializable {
  /**
	 * 
	 */
	private static final long serialVersionUID = 4519883794457262405L;
String message;
}