package pl.mosek.applausematcher

import pl.mosek.applausematcher.domain.Device
import pl.mosek.applausematcher.exception.NotFoundException
import pl.mosek.applausematcher.service.BugReportService
import pl.mosek.applausematcher.service.DeviceService
import pl.mosek.applausematcher.service.TesterMatcherFacadeImpl
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class TesterMatcherServiceImplSpec extends Specification {

    @Shared
    def deviceService = Stub(DeviceService)

    @Shared
    def bugReportService = Stub(BugReportService)

    @Shared
    def testerMatcherService = new TesterMatcherFacadeImpl(deviceService, bugReportService)

    @Shared
    def iphone4 = new Device(1, "iPhone 4")

    @Shared
    def nokia = new Device(2, "Nokia 3310")

    @Shared
    def macbook = new Device(3, "Macbook Pro")

    // This "tests" should be treated as a showcase, because of the project simplicity
    // Normally I would go for fully integrated tests using memory h2/test containers solution
    def "would throw NotFoundException"() {

        given:
        deviceService.findAllByDescriptionIn(inputDeviceDescriptions) >> dbDevices

        when:
        testerMatcherService.findMatchingTesters(["US", "JP"] as Set, inputDeviceDescriptions)

        then:
        thrown(NotFoundException)

        where:
        inputDeviceDescriptions                          | dbDevices
        ["iPhone 4", "Nokia 3310", "Macbook Pro"] as Set | [iphone4, nokia] as Set
        ["iPhone 4", "Nokia 3310", "Macbook Pro"] as Set | [iphone4] as Set
        ["iPhone 4", "Nokia 3310", "Macbook Pro"] as Set | [macbook] as Set
        ["iPhone 4", "Macbook Pro"] as Set               | [nokia, macbook] as Set
        ["Nokia 3310", "Macbook Pro"] as Set             | [iphone4] as Set
        ["Macbook Pro"] as Set                           | [iphone4] as Set
    }
}
