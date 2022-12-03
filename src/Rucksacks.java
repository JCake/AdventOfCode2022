import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Rucksacks {


    /*
    Lowercase item types a through z have priorities 1 through 26.
    Uppercase item types A through Z have priorities 27 through 52.
     */
    public static void main(String[] args){
        String input = input2;
        // Part 1
        int sum = Stream.of(input.split("\n")).flatMap(sack -> {
            int dividingIndex = sack.length() / 2;
            Set<String> compartment1 = getUniqueContents(sack.substring(0, dividingIndex));
            Set<String> compartment2 = getUniqueContents(sack.substring(dividingIndex));
            return compartment1.stream().filter(compartment2::contains);
        }).mapToInt(Rucksacks::priorityValue).sum();
        System.out.println(sum);

        // Part 2

        List<String> rucksacks = Arrays.asList(input.split("\n"));
        List<List<String>> rucksackGroups = new ArrayList<>();
        for(int i = 0; i < rucksacks.size(); i += 3){
            rucksackGroups.add(rucksacks.subList(i, i + 3));
        }
        int badgeSum = rucksackGroups.stream().map(group -> {
            Set<String> sack1 = getUniqueContents(group.get(0));
            Set<String> sack2 = getUniqueContents(group.get(1));
            Set<String> sack3 = getUniqueContents(group.get(2));
            return sack1.stream().filter(sack2::contains).filter(sack3::contains).findFirst().get();
        }).mapToInt(Rucksacks::priorityValue).sum();
        System.out.println(badgeSum);

    }

    private static int priorityValue(String item) {
        if(item.equals(item.toLowerCase())) {
            return item.charAt(0) - 96;
        } else {
            return item.charAt(0) - 38;
        }
    }

    private static HashSet<String> getUniqueContents(String sackCompartment) {
        return new HashSet<String>(Arrays.asList(sackCompartment.split("")));
    }

    private static final String input1 = "vJrwpWtwJgWrhcsFMMfFFhFp\n" +
            "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL\n" +
            "PmmdzqPrVvPwwTWBwg\n" +
            "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn\n" +
            "ttgJtRGJQctTZtZT\n" +
            "CrZsJsPPZsGzwwsLwLmpwMDw";
    private static final String input2 = "rGnRRccfcCRFDPqNWdwWJWRBdB\n" +
            "jZzVVSZSjmQvZTSZfjmLzNPJqWtJBwqpNtBpdWdNvd\n" +
            "fZfjlMLVshMFhcHnDG\n" +
            "vJRmRfJbfRfJsCpvgLggNVsv\n" +
            "zlzSrMPZMgBFFMNMVWsjsF\n" +
            "dzBSBlzdcggRGdndnn\n" +
            "hNwqVDVDdmQwQPrbDMSSMRSWSM\n" +
            "LvnzJJtlcHstlffCVpMSbRMpBMbCgVWM\n" +
            "lGvvscLHcfsHtvlsnZmmNGhNFVQqqTdqFd\n" +
            "sHGzGsfcZnHfhbLRFrdrhRFf\n" +
            "vwVqzSSjSSttjSqgNMqwzSSVCCgRPhPhFLdCRLLrdCRRrDLb\n" +
            "vvwwtvvVwSvMQzjvmNtJsHBnsZllmnnTBBcBlTnW\n" +
            "pzNVBVplhfLfVfVStZrZHbQHZQTb\n" +
            "sdPPJdCjbdQHMTWt\n" +
            "ngmJGFjJwJCmGcnJcFtgwcDBNLlqfBpvfBpqllhlfL\n" +
            "TGpphMZhJQpGLZTMCCtsBMCSDsStBFcB\n" +
            "jdfgHClHrdrbWvWgjqmctSDqltDsFFDFBc\n" +
            "dNWWvfgCLzzNNLZz\n" +
            "vvHzBrTSvHFbqZqZTBBtzdVfGCGhJSfGJGDSdGMhVG\n" +
            "lgmgslssslscsnRlVGWGWGVMRCll\n" +
            "pQPsjwnNgQmnNNwQgNLNnmgZqvFqtwqtrMMzvzwFtrqTrr\n" +
            "TNhNLTrswwsnFNrrGZVnJnZmVpSjjVDnPp\n" +
            "dBlzzCBqgfWMBpPSJDHVZSVP\n" +
            "cqzfpcdcNFFGrhcb\n" +
            "LLpzCzSzJnLQVnTNhQ\n" +
            "vtDqwBcRztQbthtV\n" +
            "qZZZqZvvsZwwjvjDjspFlPpSSCslpzPlls\n" +
            "rsZVlmStlFZllvmSSvLRqcGMswMMzjMsjqGCjzMG\n" +
            "VTPgQBQdBCwgjCnJGC\n" +
            "pBNTNHdbdWLrDVLbDmLZ\n" +
            "CFmsmwnCRmjCjnCzJZQhhBGQBsMzbP\n" +
            "WvltTWVDdNttdlDbbZzJvPJvBBhhzM\n" +
            "gHDfTNlDDdgVNdglgfqtpcSFwccCmmwnRSwzzHHF\n" +
            "hgmhWgCdzpjPCzFC\n" +
            "NVfgffZblZQVNtFZPntPDrJjPt\n" +
            "TwbQLLfMgdhswWGH\n" +
            "JStSPHPJNrBCtBZMPtHTVfVwLcbbLTcSgfbVfn\n" +
            "GdhhCplmdWbVddLV\n" +
            "qpFvhQppFRlqmFGppRhqvvFzZtQMHZMzBNBCCzNDHHMPzB\n" +
            "MvCMQmJCMDQjwMJCSJpQmDcnGBggfsgljGNslsNGjTHTNf\n" +
            "HRPtRRzVWWFhrFdZhTGlTTLLggBLVgGGLf\n" +
            "zZhdWZPWRdqttWWRPzRPmcpmpQqvvSCvwHwQmJqD\n" +
            "NwrVVWrvwtQDRdRqWbqh\n" +
            "jCCZjlJZZGclGPpCJlcCBhJshgqTsQgbDdshTbgLRh\n" +
            "ppppflPZlpvfffFzHqFV\n" +
            "VmTwGVwnwHnSnqGSVmBBwwmTZvzbCrsNWcsZsqFzCWsbWrNv\n" +
            "JMPPgRDPDgghJggtzsMbZvsNCrWcNsbM\n" +
            "DhgJllPPJcgJpptDDDPldpmTwjHBmSjBBwmBwHBdBmTQ\n" +
            "vblvFHvrQTjqqhCpFwLnFL\n" +
            "RDJRWsdRdgdWZMCMQSVppMJpVL\n" +
            "DzsgRNNsftWdQmcvbPcfcBfj\n" +
            "wTgbsTmgqSbzSlSvFb\n" +
            "rNdDdZnRtJFlmVSHZz\n" +
            "mfNjjmmdNWhWCsffwC\n" +
            "LltBNFLHHcJcfNdNTwbbNsfW\n" +
            "vQSMCzQSGdSdvjsTwmwfwWwV\n" +
            "nRCSzMCQDqlgdlRJHJ\n" +
            "vgqTGwnhpLFsVvrR\n" +
            "ZZzTHlTlJNbcLfRtppFzfs\n" +
            "PlWBWbjQjJgCDCMPTTGT\n" +
            "wtBdWdDpjDdwScBtnsFsPmmnPbPHHPghhh\n" +
            "fSSVGVzlTlqGfffLTQMGHsmFsbmPFbsPbhQZFhhs\n" +
            "TzLzfqJTVRGvRMvqvLGzMqRfSvcDpNjcjdcwSDBDtBDvSCDj\n" +
            "FrdCCzmqFrpDRTRHRLnQJJnr\n" +
            "SGfNRWlBZlgtRltGbvnTVbJnSHvLHVbH\n" +
            "NRWsfWBwwqwDMDpc\n" +
            "vSfsmsdssdjSdZdSgRnmLRGNzgGnqwwB\n" +
            "VrlThFPTPjHFDPRqGGnRnNLqqqHG\n" +
            "lPtTDVCCDrjppfdSQbcJMsbftv\n" +
            "RFFslstRRfSljtsRJPjRtRtngSHbmbbhGbHQQmgbmgGhGQ\n" +
            "DqBZdWZvTdMBBTLDzqBhFnvgVhnHHFbnQVnGnb\n" +
            "wcwMMMTzZwsjtCtjFN\n" +
            "CsscSlwwZDscMNhhpZhRZHRM\n" +
            "VjQvjbQvbhJVbvTbnQTnHHLrRFRqqFqFVdMHNHNH\n" +
            "QbTjjbtJnvttntjgjJjPDwwsBsSsSlslhlwwPCBS\n" +
            "RLjhhBBcNNBNhNjhpwScwSCTFwcFvMwvlc\n" +
            "dJqPJqbqtmgmgdPrdgwvDTCSSlMSFDlFTzFg\n" +
            "stCCqmbGrrbmPsqZhHhpjjNhfjBnnsnp\n" +
            "LfDzcMMVsDLLzNscGhFFcQhlhhBRll\n" +
            "ZCbSbwdmwPnPnPmHjdjWFWsWhdGjdH\n" +
            "bZnPbvnvqCvJDrLJNssf\n" +
            "bbldQHVltHQSSJJmTlwJGjCjCChchgMhHhprCpMv\n" +
            "WNPDFfqfzFsBFFnmgjGhgccjpcpgMGrs\n" +
            "mZFFzDnmzBBZltdlZSVtZS\n" +
            "hdJZZdJTvnJmRphvvpGnwvqzVFSwVlHVlFHFHNlzlgFgHF\n" +
            "QWWWBbrCCrtjQsMdcWMBfttHsgFHVHgFSNzDzFlVLggHzz\n" +
            "jQdBPCtrtcPcMQCTnqnhTvPGGvnJGR\n" +
            "ZhsmsbNVmFssmblMMpdvvQdwLQRppRvQ\n" +
            "GjnNNGNCGjfJcqrQDvQQpvQLPpnQQw\n" +
            "BJjWqWSCrNHrqjfhlHzlVbVtFmtHTV\n" +
            "mtJmPmQBbPFshnJzZpLZ\n" +
            "gMqMHRGrCHSvrRGrqPZLnplFzlVhFspL\n" +
            "RrDrgMGRDPNtfmDN\n" +
            "wqqvqVmlpqchqrDD\n" +
            "gzRltSjgFlWshrdngrpPcDcd\n" +
            "RRjtsbGfGLbsLWSLtjzGSHNNHmHBwNNHNmNfllwVfl\n" +
            "mqFBQgnMQQbJqnTswSMNWGsDswZZ\n" +
            "HgzlRzHccfHsfTwSWfNSNf\n" +
            "VLldvpHLVHrFJFJgpnQbJn\n" +
            "SzCJtLdDCCtqCcdDfZMVMfGVbsVZPPzz\n" +
            "wpwWQWjQgwQgjNBwgHQgsGfPfbPsZPGSPjZmrPrV\n" +
            "QFwpTwpHlnHFNFWQWlQgNwNLCddvDShtLJnSJCLqJJttDt\n" +
            "HMgCVHggtqMMvjgNjNCMMwfWfPwWPJQQNzWwJzlQlQ\n" +
            "bGFrqrFGZLLdFmSPcmPzQJlQcWzJmJ\n" +
            "hRphhShnLGFdGSFSLRGrdqdqsjTtsBTVTTBnVjVvHHBtHggt\n" +
            "hHhnFHQpcFcHjcjfZfZRnjjnNNBvNNNtwvNtbwPtNcPtBgbg\n" +
            "zzVLWCHLSdCttbvw\n" +
            "mMLsHDMVFlRhhmhm\n" +
            "MHMgBNQQPCMMQBbBQQgJHbWttdlfWpZJVWtGGztfWJZW\n" +
            "nvLzDTDFDFqSqncTFddLGfWfssVWWVdlGs\n" +
            "njScSmcnFDDhnmcmnSBzMggjjMQrQCjBQrwH\n" +
            "MFVFHqhHHfVVSGcVQCLttttWTtLQ\n" +
            "BgdJJrJzbGpLssCtTLLCpC\n" +
            "PGBGdjjllBGBjhmhHRlmSfhRDF\n" +
            "BLJmWwBJDDmLDFnVPzvTttvNhNFsHhvvQH\n" +
            "cjbSqfWbRrbSzNMjhtNzzTsT\n" +
            "dRdbgflfqmWggGmwWW\n" +
            "lBTTnDMnNwWdpwMllTMDdTBTSRJjRRcSJRhRGhwtccScgjtg\n" +
            "vvsnCVnHHnsvPzLfVJjtShgGJGVJSc\n" +
            "zQQvzCZbsnbHCWMdFpMqblBqND\n" +
            "CLggQjStSQjLgVRfhBRztwpBpt\n" +
            "DNVmJDFcGNGlmNDnGFnsGcDdwzzZwZPsBPZZhhfBTpwhPfZh\n" +
            "DMDccrnrdnDJcJGFGmCqbLvQqHVSSQVMCgHV\n" +
            "rZVNNDrCFCbjbRSgjhZv\n" +
            "czcMTcGMlcjjvvvGdhbb\n" +
            "pHMpHtLWHHHztMzsvqvnVqNvsnNDqW\n" +
            "hPhPBQVjzjQScjChRVVVsdfbvdmvvpGmvfdbff\n" +
            "nwZwZWNTrwLTTDtbfmHDpGccstDv\n" +
            "FLFJNllFrwTgTwNLnwTlFPhcjjhRhSMSjjBPhzJPQq\n" +
            "CgJCHgJfHzGrrMjJpl\n" +
            "SWqQLSQqdFSLrrCSZrvpzjMM\n" +
            "QLWqhFLQdwFqnPbHcCHPbhCh\n" +
            "jLplfcfjQfptPtLTTtPrRqCCCjZvhBhwhDjwhBBBqC\n" +
            "msznRWgSmmHbSJwDvvBqCCqWFF\n" +
            "mzdsVnbbSSznmbGgGSmTclPcrQffdLfMTLRLtl\n" +
            "PpQQFdNFBtdsFNNPPthTtldwGMnZVvmbbVGbMwrGvG\n" +
            "wHWRJDjHHRgJDZlmVZmnvDbDDM\n" +
            "JqJfgRWLSqWJfcsBfwPpPwFpNwfC\n" +
            "SjpVgjghVZvssgsHjQfHcfcnfNcnbqcRbr\n" +
            "WBtNWTWNJnCTCbRbQR\n" +
            "FlWGtwPJmJPBmwFtMGmpNhDSSSgZhSszzghsMD\n" +
            "fSfzvQSbbSSSTQQzDQTzHsqHmjHVFsjqVJsbrrLs\n" +
            "GWZncGGdBwlPGPJcGlBcPgNGqLqmjsHMVLrrVMwjsMMssLmr\n" +
            "ltGWNggZJnCRhDtzvtTt\n" +
            "zzSHMgsPWzLSJQMMWQWLJBLVqcmVrzmvcpFcqmqpmFprFV\n" +
            "TlDnwhDblbnbbtbjdpVCrmFVDgmprcRCcF\n" +
            "hTdjgdldgdfZWWGGHQJHWZQH\n" +
            "TZVsSRGsGMGWZTvpmrgcMCmrwwFFgF\n" +
            "BDPPnDDLDqDFLNLgctLNrm\n" +
            "PzDPfHPdDPdJPfdHJqdbnnSVvRvsjZSvbsrRGhvGRhTG\n" +
            "nnghnhLhgdVqSPLHcPHPNtpmrRptNt\n" +
            "DwvMWlsJlGzsGMlvsZcWWbRrNHNtZtHttHNTNttTrTTR\n" +
            "DDzDcwlWWGlcsszGwBCggFqgghCBfqCFdhSh\n" +
            "GzgQpJnwgJfbSWpSvqtvlBtmSLmLlvLS\n" +
            "zCHsRZjHdzVPRFNlLlvtlNMNNrtDrt\n" +
            "zzZVsCcCCHzRhcbpwGJGGpcf\n" +
            "lVQMrwlMwwMCBZmFtthmVmsgWhTL\n" +
            "bdzHSSFJcvzcpFDptsDDmhTgLmnmmmhT\n" +
            "SFpcGvdpddvGlBPZMCGrBZ\n" +
            "QmQTQTFTSQPNbsPjPnntZjjnnDlBBB\n" +
            "JHqJqhfCJpWfpHchRzzCnGBjtjDZltsZpljGntrr\n" +
            "chdMHqHcMhWMfRczMJmmQsVTSNbNNFbdQNST\n" +
            "HHdFqqDRdNsHfNRsjWPCBcCCZPwDCZVBVc\n" +
            "lhlgprMrlprbplzwZQPwwPjbZZCPwP\n" +
            "lpljllTGGGglhThpjSvdssSnfRRTdRnvsN\n" +
            "sDzjCqLLzddjsdRsSShgmtnCgFnmnffmmFcf\n" +
            "rJbqJMTqJGVTrgnFmfhcfmnJgm\n" +
            "GQbBWZGbVrqpqWpZZHlwDsDsRsdDlDRDBDsD\n" +
            "LbLbvbhQgblwwqbjGG\n" +
            "cFTTMsczJsTWJFfNNlVqvDqjNvlFqZ\n" +
            "mfMvJHvmdLgLRgpHRR\n" +
            "cvhRpWWhpfpcTpWvRcRcWVNwsjNLJFsJjwLtMLdsdsLJjL\n" +
            "ZZPqGqgZrllbbVMtnJtllwJtltnj\n" +
            "rrPCSQHbbrSqHqqZGGQbvzBVTpfzmvBvcvRCTpzT\n" +
            "VMzNNhWVlrbJHbjcJCjCSR\n" +
            "qgtDBgBtTGjqJvSPRJHCqHcd\n" +
            "GfnfLjjgGLmgBWpWrMMnzWZppl\n" +
            "zRtfBftCvvPSvPclZgcgJznWcgnq\n" +
            "dDpGpVdwdGphbDMpdQhnJjjqnZQWnZNcNWlqlJ\n" +
            "wdFdTDpGDSCmTtRqRq\n" +
            "bTGrRzjbmbmqsGDDjHPpWpfjHJZFVPJp\n" +
            "LwdwNNgMLfZTdCHPPd\n" +
            "lhtwtvttgnchcsrvmzTvmsTbbm\n" +
            "dfLjdlqLtqpbpPQpHS\n" +
            "ZGJnFZFDpBWWGBTzzrhmhHzHQPFPvQ\n" +
            "NgJGGGNGnJWgMDWGgDpWnZWgwdRCtwCCRVLdjwVcccdwct\n" +
            "MMtzRCTnVVnHbbMNrHMRRVQJFrPPDsrsJgjjQGJGpFFF\n" +
            "ZcqWqdfcmfhwDgGpDJmmQDQs\n" +
            "LhwBddvlddvdLCgCtCTzgzMN\n" +
            "ZffpWcfPcPrTwlVCvDhhcS\n" +
            "GMLBNHjMBGtmjtMQMJjLHTwwsSswdslhGwDhsDvCvV\n" +
            "tQgNRHMHQggHjQttbZqfFqrnqSZWfPZR\n" +
            "MDqbPdqGfwGbfMswqfJdPGgQpCZvQgmvJFCmQJQvRCQv\n" +
            "WFthczzzrpWgpHlRRC\n" +
            "BhLSFnFrcLzhtSFnSTznhGbMGwjGwjjPbGDqjdqTMd\n" +
            "zbQdJbBPTsWcCgdmfC\n" +
            "DNqZHjvwZMShDhwqvLmlgVnLmcnsfnVf\n" +
            "HHSFSjNZFqjZrhQpPtzrBgQzPrpB\n" +
            "BzzJHvJJvWsgzPPTWPSJsJgNtmMtNFlvNZFltFtttjmtVZ\n" +
            "cbccGnqhwhdpqRnnrdqdntZVFfMCltMtGFCmtFNFlj\n" +
            "wnlRRQbnpHQWHgSQTs\n" +
            "SQQBFnPzSnQVSzFSWlzlBSWFMsqmMmzLLChTmmMqzsTChmss\n" +
            "ZgdwrJdtJrgpcCwZNbbjsGhGDvMrrqmmMmDhTsqv\n" +
            "NtRZwCbZbbjRZNJcRbjWVWPnWHQWVPBQnHlfRB\n" +
            "jLtlpljLpsbRnDNtBpbjdqWcqChccbhqChqbWQbQ\n" +
            "gVwwggvJJwBqgWqfBCgT\n" +
            "VHZrPSHwzSwvDLnnLlsrDRBp\n" +
            "GBDGDrhwVrFhVCVBvhhvwBDVcmlfcPcMMmmmqNTScNNNflSF\n" +
            "RzRLRbddjZTbTQjJSWNzlNWNcPqNWqSf\n" +
            "dHjRgddHnZngjHLnQsgZHbGrrVpwpTtThnDBppVCTpDp\n" +
            "dpjwvdLwtvJszhmzhRVj\n" +
            "QfFQrffPBCBZMQrMRWzmzmVWVghVLs\n" +
            "GBPGBrFrHZTBSTqvvLNtqSpq\n" +
            "zFcGnQcZPZncbbbhPncpNCwzvwmjMvwwmwmpvd\n" +
            "tBRtdrRTJNvRjMMwRC\n" +
            "rlWSqVtTHqtVTrngQnfbQFdcghgW\n" +
            "RMjfvsbQPvvNpLmLprFJFrFJJT\n" +
            "SqCtGCcZZCwcCqqcGdWGdSZmMTmnVBdLnTTgTrMgrgVLTB\n" +
            "lGzGzHSCzHctsQRMsQPzDjNs\n" +
            "FQTWdTVMDWWVWFDTFcVcWJqTlCCCSlFmtCmNZStsmFmCwCgm\n" +
            "nPLbGZzrgwBlbBlS\n" +
            "PppGRnzPnpzvZVDJVvTfDVVVdD\n" +
            "CQlDsWWfflWDMMhRmTGqFwSjJFdqwSQJzF\n" +
            "PgpVbZPcHgBcgZrBZcHNTTbdqTGjSqFjRFjwSjGG\n" +
            "VvgRBBNLRrPhsfhLWLWDtW\n" +
            "hHhZDcDcPZWpLZWpCQ\n" +
            "pbqdwmbqqmBpdMsgdqjCGvfCWGTWLGmWfGQtVt\n" +
            "wjjbsFBRbwFgMpDznPDrrFcShr\n" +
            "zGmsJQsDBBmDDJJpRZzSqZZPRffWRSqh\n" +
            "LQlVHjCCNCLRSgWlZnhPZW\n" +
            "bLtHCHtjVHQDTJcGDQbs\n" +
            "QPRlnHfPllgRfnqhcwgwGMGzBWzBBBBB\n" +
            "LCtVCZtDbdttjZFtvjvdDMGGBmBWWZWBBWGSsWJSJm\n" +
            "dFVNTVbTjMnHlThHnpHR\n" +
            "hGhZLlqmqZWTcWrmWqrWmTrqjQVQwNHPgPwPPSgPjNwVSLjD\n" +
            "bsMJBRMvRsvsJMRRbspFgQwSNNSNwVNjgwDMDgHH\n" +
            "pFnFtvCQFsbBQzsBrmnTWchWZqWqWqGZ\n" +
            "wpwlJdCWJWdzlWGRRcrDrwRqrqDFrF\n" +
            "ZmPsSVnnTvmHDgFcDTHFTF\n" +
            "smVvnSQhbsNsvsmsnQQclzGCWpphppLJCdJWBpll\n" +
            "FfSmMJmBDfBjDSjFtBVmftBQPwPhPCbhQvbhwCCbvhhjhq\n" +
            "ZnZHZgclJWNbwbcbcwPrQv\n" +
            "ZNNGLHzHNZTTJnlFFfVmDBsFSVLdSD\n" +
            "DDBvjMvBJMtWjNRNrvdtbshTdpssdPSgpFpP\n" +
            "LwGQcwLGJTSssQbg\n" +
            "wcfcCcmHfJmcLLVZVqBBBqWDRqBRDWzHNN\n" +
            "LhnpcdcdrChplvllHptlgR\n" +
            "TsSTSBqPBTGNzqGfzfffGfVtPlHHvMDtHtRMlvjHHgtv\n" +
            "sBfWNGQmQbCgQhQn\n" +
            "pnnHngqsRjstjRgtrBDlBwDgwDZBldlD\n" +
            "SFvJMGhhvcbMSWPJbFzVDdzDZwDFlFlzfFBV\n" +
            "JJNWGSWSMNMCPhcvMhGStwtjtRRtRRqRsCtQjqRj\n" +
            "nQZfJswFffjJplqhlqZlVVhp\n" +
            "vtdCvGdBCzVHgnzLDlpL\n" +
            "BbCTGvtGnBBCPjrsccjrwbrFjj\n" +
            "wjjvDQwWvSQDLbwfNrrJcMHrczcpWN\n" +
            "tTnqlRsTRthFhnnpfNrmcTNMzfCzHr\n" +
            "slsBGVlqghMRhsRlltsFDgQSDbQwPSPLQSZjPSLQ\n" +
            "PcQmmQRQZRFQPjjDWhgCgWdM\n" +
            "nBGtbGNBBGvJbbtpWhCNCjmMjHlWHDdM\n" +
            "vbBnBBrrvVBtJJbvtzzptRQLLZLRFfqLSTSfzqwfqm\n" +
            "PBFZDFPsDZBnTTBdDHSNSpNzmVTVmVGlNH\n" +
            "WvqFLWQCMQLMRtQJFWQLvQCJVzmNjNzVCllVmGSlNHlVzljH\n" +
            "WRtrhtWQWFbrrBwBZrbn\n" +
            "MwWnMPnMPNswjPDvRbsblCGFZGZF\n" +
            "JdJVVVtLdgZhvGVBDZhZ\n" +
            "rqmdqtgcLQLTfWffwGGmmp\n" +
            "QQhhWzQWsMhZjbbmffgfjrGDdfdGvv\n" +
            "HnCJVHcnnHttCRVRCcnBCqJBGfPmTvTvdDfvqfrddNDDggGG\n" +
            "wBwwcRBBCJpFcFcpnVVFQWQLSLbQZLmhzshMlMwQ\n" +
            "CgDNbsDcHjTcgDCgjRHMJrlHFrBHFmrttrGGtFwG\n" +
            "VfQJnvJdhvSJZphSzWpvSzZSltGGBllGBqPPFPrwrmfqtFFB\n" +
            "VJLvdQhphhQnjLsjDDjDcRbL\n" +
            "LjljTPdLdccLhcMZhTTMdzrrtzGgtvrgnttNDGrWtDgn\n" +
            "hbCmCHqHmSbRgNrtvCgGgttN\n" +
            "SJFJRFpHqwSFSpsHwbHwRhSJPTjMMMPdPlPLcVQscLVQQVlL\n" +
            "QmTTQVqrVrMvbCwLczbRlQ\n" +
            "sSNtNGZFjBSsjSSShcRvwCFLlzWcWRzCWv\n" +
            "LLNjZhSGZBnjhJjjrrTgPqgPgTfqfJpf\n" +
            "DCCjDDtHVptCtvMZlJbSnScWWfHlhW\n" +
            "qsTFmTgmqRRLswQGmfWwSnZSSfJSSWZcWb\n" +
            "dsmqgqdsNgTFLFRLGmRpBtBDtDNVpPCCfVrtpD\n" +
            "LLNRhHhRbsNGjqCBPBrLCw\n" +
            "lgcFfvWGTllzfJVVFVWDzFqqMrZCMBrZZqvSrCPZSSrr\n" +
            "fFGGlTTTlzWQGzzDFttQmHnpnhtmmpRssm\n" +
            "LZNnBgtlNZztzmGHmpHHPPPm\n" +
            "QwjjQRCQScbFFFchhFrFjwmsNPHSSWJGsGppMSWMmWqs\n" +
            "dQCwwbwhrjQQjCwRwbhRBlDDfBtVlnNnlgLdnvvd\n" +
            "wRbGbqqGCwnGTRqBqlMVphpgMgMFdnFVnt\n" +
            "cgzvssscgHJVdhDdhDMvDM\n" +
            "PjcZcsJrJHWgrPBQmCqRBPSSRCSb\n" +
            "rHBmLlPLlTzztvRtGsVL\n" +
            "NWJJWWjJDJWWhphqwCFCwzvRVcgRtctRgNNVVscsGc\n" +
            "hqCCsnCpCDnbCnWhwpbHbBHmMBMMmdPrZfdP\n" +
            "GRPprPQdsprGpGgGTlqfVqnZLgnLnwNZLw\n" +
            "CCWJMMvhhCvthtCjvDWFjMcCVZJLNnfqnllwzlzNnzzwVNnN\n" +
            "cDtZFjDjcMCDDtZFSMCvvDpmsmSRRpmmbSpdPRdTmTsp\n" +
            "mmPpsbZZbbzvzgbrZRPgPMWqtHtqDnGCnMWCDwGHwtwW\n" +
            "cBFBNNccsTLjJjfcjfGDGQtWwFCnCGtqCCQH\n" +
            "TNsTLJlffdldzvrmbmrPzp";
}
