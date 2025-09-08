package com.example.models;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Note {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@javax.validation.constraints.NotBlank
	@Column(columnDefinition = "TEXT")
	private String title;

	@javax.validation.constraints.NotBlank
	@Column(columnDefinition = "TEXT")
	private String content;

	@Column(columnDefinition = "TIMESTAMP")
	@OrderBy("createdAt DESC")
	private LocalDateTime createdAt;

	public Note() {}

		@PrePersist
		protected void onCreate() {
			this.createdAt = LocalDateTime.now();
		}

	public Note(String title, String content, LocalDateTime createdAt) {
		this.title = title;
		this.content = content;
		this.createdAt = createdAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
}