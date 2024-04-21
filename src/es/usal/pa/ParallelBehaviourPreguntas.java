package es.usal.pa;

import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.ThreadedBehaviourFactory;

public class ParallelBehaviourPreguntas extends ParallelBehaviour {

	public ParallelBehaviourPreguntas() {
		
		ThreadedBehaviourFactory tbf = new ThreadedBehaviourFactory();
		CyclicBehaviourEnviarPreguntas CyclicBehaviourEnviarPregunta = new CyclicBehaviourEnviarPreguntas();
		
		addSubBehaviour(tbf.wrap(CyclicBehaviourEnviarPregunta));
		addSubBehaviour(tbf.wrap(CyclicBehaviourEnviarPregunta));
		addSubBehaviour(tbf.wrap(CyclicBehaviourEnviarPregunta));
		addSubBehaviour(tbf.wrap(CyclicBehaviourEnviarPregunta));
		addSubBehaviour(tbf.wrap(CyclicBehaviourEnviarPregunta));		
	}
}
