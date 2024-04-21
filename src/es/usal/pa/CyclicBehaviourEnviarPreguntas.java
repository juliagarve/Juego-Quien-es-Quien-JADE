package es.usal.pa;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class CyclicBehaviourEnviarPreguntas extends CyclicBehaviour 
{	
		
	@Override
	public void action() 
	{
		//Espero conexión del cliente e inicio de ronda
		ACLMessage msg = this.myAgent.blockingReceive(
				MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.REQUEST), MessageTemplate.MatchOntology("ontologia")));
		
		msg.setConversationId(msg.getSender().getName());
		msg.setOntology("otraOntologia");
		
		DataSource source;
		try {
			source = new DataSource("famosos.csv");
			Instances dataEntrenamiento = source.getDataSet();
			
			 if (dataEntrenamiento.classIndex() == -1)
				 dataEntrenamiento.setClassIndex(0);
			 
			 J48 j48 = new J48();
			 j48.setOptions(new String[] {"-C", "0.25", "-M", "1"});
			 j48.setUnpruned(true);
			 j48.buildClassifier(dataEntrenamiento);
			 
			 double clasePredicha=j48.classifyInstance(dataEntrenamiento.lastInstance());
			 
			 Pregunta pregunta=new Pregunta(j48);
			 
			 while(!pregunta.esNodoFinal())
			 { 
				 String temp=pregunta.obtenerPreguntaNodo();
				 ACLMessage mensajePregunta = msg.createReply();
				 mensajePregunta.setContent("PREGUNTA " + temp);
				 myAgent.send(mensajePregunta);
				 msg = this.myAgent.blockingReceive(MessageTemplate.MatchConversationId(msg.getSender().getName()));
				 pregunta.navegarNodoRespuesta(msg.getContent());
			 }
			 
			 ACLMessage mensajePrediccion = msg.createReply();
			 mensajePrediccion.setContent("ES "+ pregunta.obtenerPreguntaNodo());
			 myAgent.send(mensajePrediccion);
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
