package holo.questions.demomusicprovider.domain.ports;

public interface Client<T, R>  {

    R executeCall(T request);
}
