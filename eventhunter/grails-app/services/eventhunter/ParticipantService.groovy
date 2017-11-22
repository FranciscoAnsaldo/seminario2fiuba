package eventhunter

import grails.gorm.services.Service

@Service(Participant)
interface ParticipantService {

    Participant get(Serializable id)

    List<Participant> list(Map args)

    Long count()

    void delete(Serializable id)

    Participant save(Participant participant)

}