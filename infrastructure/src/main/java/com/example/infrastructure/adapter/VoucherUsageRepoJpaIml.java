package com.example.infrastructure.adapter;

import com.example.domain.entity.voucher_usage;
import com.example.domain.repository.voucher_usageRepo;
import com.example.infrastructure.Repositories.VoucherJpaRepository;
import com.example.infrastructure.Repositories.VoucherUsageJpaRepository;
import com.example.infrastructure.mapper.Voucher_UsageMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class VoucherUsageRepoJpaIml implements voucher_usageRepo {
    private final VoucherUsageJpaRepository repo;

    public VoucherUsageRepoJpaIml(VoucherUsageJpaRepository repo)
    {
        this.repo=repo;
    }

    @Override
    public List<voucher_usage> GetAll() {
        return repo.findAll().stream().map(Voucher_UsageMapper::toDomain).toList();
    }

    @Override
    public Optional<voucher_usage> findById(Integer id) {
        return repo.findById(id).map(Voucher_UsageMapper::toDomain);
    }

    @Override
    public voucher_usage Save(voucher_usage u) {
        return Voucher_UsageMapper.toDomain(repo.save(Voucher_UsageMapper.toJpa(u)));
    }
}
