package it.lavori.gestione_ruoli.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import it.lavori.gestione_ruoli.model.Utente;
import it.lavori.gestione_ruoli.repository.UtenteRepository;
import lombok.AllArgsConstructor;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class SpringBatchConfig {
	
	private JobBuilderFactory jobBuilderFactory;
	
	private StepBuilderFactory stepBuilderFactory;
	
	private UtenteRepository utenteRepository;

	@Bean
	public FlatFileItemReader<Utente> reader() {
		FlatFileItemReader<Utente> itemReader = new FlatFileItemReader<>();
		itemReader.setResource(new FileSystemResource("src/main/resources/utenti.csv"));
		itemReader.setName("csvReader");
//		itemReader.setLinesToSkip(1);
		itemReader.setLineMapper(lineMapper());
		return itemReader;
	}

	private LineMapper<Utente> lineMapper() {
		DefaultLineMapper<Utente> lineMapper = new DefaultLineMapper<>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setDelimiter(";");
		lineTokenizer.setStrict(false);
		lineTokenizer.setNames("nome", "cognome", "email", "password");
		
		BeanWrapperFieldSetMapper<Utente> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(Utente.class);
		
		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(fieldSetMapper);
		
		return lineMapper;
	}
	
	@Bean
	public UtenteProcessor processor() {
		return new UtenteProcessor();
	}
	
	@Bean
	public RepositoryItemWriter<Utente> writer() {
		RepositoryItemWriter<Utente> writer = new RepositoryItemWriter<>();
		writer.setRepository(utenteRepository);
		writer.setMethodName("save");
		return writer;
	}
	
	@Bean
	public Step stepOne() {
		return stepBuilderFactory.get("csv-step").<Utente,Utente>chunk(100)
				.reader(reader())
				.processor(processor())
				.writer(writer())
				.build();
	}
	
	@Bean
	public Job runJob() {
		return jobBuilderFactory.get("importUtenti")
				.flow(stepOne())
//				.next(spetTwo())
				.end()
				.build();
	}
}
